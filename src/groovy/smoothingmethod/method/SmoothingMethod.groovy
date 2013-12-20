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
    private Task task
    private List<TaskData> taskDataList
    private int taskDataSize
    /** Параметр сглаживания */
    private double a

    SmoothingMethod(Task task) {
        this.task = task
        taskDataList = task.data.sort{it.id}
        taskDataSize = taskDataList.size()
        this.a = 2 / (taskDataSize+1)
        a = 0.2 //TODO
    }

    /** Расчет метода */
    SmoothingMethodDataWrapper calc() {
        println('>>calc()') //TODO
        List<SmoothingMethodData> smDataList = new LinkedList<SmoothingMethodData>()

        Double UVar1 = getU0Var1()
        Double UVar2 = getU0Var2()
        Double sumAvgError1 = 0, sumAvgError2 = 0

        taskDataList << new TaskData(title: 'Прогноз на Ноябрь', value: taskDataList.last().value) //Иметь в виду, что влияет на size() //TODO
        taskDataList.eachWithIndex { taskData, i ->
            SmoothingMethodData smData = new SmoothingMethodData(title: taskData.title, value: taskData.value)
            smData.expAvg1 = UVar1
            smData.expAvg2 = UVar2
            smData.avgError1 = calcAvgError(taskData.value, UVar1)
            smData.avgError2 = calcAvgError(taskData.value, UVar2)
            if (i < taskDataSize) {
                sumAvgError1 += smData.avgError1
                sumAvgError2 += smData.avgError2
            }
            UVar1 = calcExpAvg(taskData.value, UVar1)
            UVar2 = calcExpAvg(taskData.value, UVar2)

            smDataList << smData
        }
        taskDataList.remove(taskDataSize)


        SmoothingMethodDataWrapper dataWrapper = new SmoothingMethodDataWrapper(taskData: smDataList, forecast: smDataList.removeLast(), a: a,
                sumAvgError1: sumAvgError1, sumAvgError2: sumAvgError2, epsilon1: sumAvgError1/taskDataSize, epsilon2: sumAvgError2/taskDataSize)
        dataWrapper.forecastAccuracy = checkForecastAccuracy(dataWrapper.epsilon1) && checkForecastAccuracy(dataWrapper.epsilon2)

        return dataWrapper
    }

    /** Проверка точности прогноза */
    private static boolean checkForecastAccuracy(Double value) {
        return value >= AppConst.FORECAST_ACCURACY_VALUE1 && value <= AppConst.FORECAST_ACCURACY_VALUE2
    }

    /** Начальное значение U0 (Вариант 1) */
    private Double getU0Var1() {
        return taskDataList.sum{it.value} / taskDataSize
    }

    /** Начальное значение U0 (Вариант 2) */
    private Double getU0Var2() {
        return taskDataList.getAt(0).value
    }

    /** Расчет экспоненциально взвешенной средней (Ut) */
    private Double calcExpAvg(Double y, Double U) {
        return a * y + (1 - a) * U
    }

    /** Расчет средней относительной ошибки */
    private static Double calcAvgError(Double yFact, Double yCalc) {
        return Math.abs(yFact - yCalc) / yFact * 100
    }

}
