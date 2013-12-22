package smoothingmethod.method

import smoothingmethod.constants.AppConst

/**
 * @author phpusr
 * Date: 20.12.13
 * Time: 16:43
 */

/**
 * Метод экспоненциального сглаживания
 */
class SmoothingMethod {
    /** Задача */
    private Task task
    /** Данные задачи */
    private List<TaskData> taskDataList
    /** Кол-во данных задачи */
    private int taskDataSize
    /** Параметр сглаживания */
    private Double a

    /** Конструктор метода */
    SmoothingMethod(Task task, Double a) {
        this.task = task
        taskDataList = task.data.sort{it.id}
        taskDataSize = taskDataList.size()
        this.a = (a != null) ? a : task.smoothingParameter
    }

    /** Расчет метода */
    SmoothingMethodDataWrapper calc() {
        println('>>calc()')

        List<SmoothingMethodData> smDataList = new LinkedList<SmoothingMethodData>()

        Double UVar1 = getU0Var1()
        Double UVar2 = getU0Var2()
        Double sumRelativeError1 = 0, sumRelativeError2 = 0

        taskDataList << new TaskData(title: task.titleForecast, value: taskDataList.last().value)
        taskDataList.eachWithIndex { taskData, i ->
            SmoothingMethodData smData = new SmoothingMethodData(title: taskData.title, value: taskData.value)
            smData.expAvg1 = UVar1
            smData.expAvg2 = UVar2
            smData.relativeError1 = calcAvgError(taskData.value, UVar1)
            smData.relativeError2 = calcAvgError(taskData.value, UVar2)
            if (i < taskDataSize) {
                sumRelativeError1 += smData.relativeError1
                sumRelativeError2 += smData.relativeError2
            }
            UVar1 = calcExpAvg(taskData.value, UVar1)
            UVar2 = calcExpAvg(taskData.value, UVar2)

            smDataList << smData
        }
        taskDataList.remove(taskDataSize)


        SmoothingMethodDataWrapper dataWrapper = new SmoothingMethodDataWrapper(task: task, taskData: smDataList, forecast: smDataList.removeLast(), a: a,
                sumRelativeError1: sumRelativeError1, sumRelativeError2: sumRelativeError2,
                epsilon1: sumRelativeError1/taskDataSize, epsilon2: sumRelativeError2/taskDataSize)
        dataWrapper.forecastAccuracy = checkForecastAccuracy(dataWrapper.epsilon1) && checkForecastAccuracy(dataWrapper.epsilon2)

        return dataWrapper
    }

    /** Проверка точности прогноза */
    private static boolean checkForecastAccuracy(Double value) {
        return value >= AppConst.FORECAST_ACCURACY_VALUE1 && value <= AppConst.FORECAST_ACCURACY_VALUE2
    }

    /** Начальное значение v1 (U0) */
    private Double getU0Var1() {
        return taskDataList.sum{it.value} / taskDataSize
    }

    /** Начальное значение v2 (U0) */
    private Double getU0Var2() {
        return taskDataList.getAt(0).value
    }

    /** Расчет экспоненциально взвешенной средней (Ut) */
    private Double calcExpAvg(Double y, Double U) {
        return a * y + (1 - a) * U
    }

    /** Расчет средней относительной ошибки */
    private static Double calcAvgError(Double yFact, Double yCalc) {
        Double val = Math.abs(yFact - yCalc) / Math.abs(yFact) * 100
        if (Double.isInfinite(val) || Double.isNaN(val)) {
            throw new ArithmeticException("illegal double value: $val");
        } else {
            return val
        }
    }

}
