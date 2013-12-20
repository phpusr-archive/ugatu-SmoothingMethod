package smoothingmethod.method

/**
 * @author phpusr
 * Date: 20.12.13
 * Time: 16:43
 */

/**
 * TODO
 */
class SmoothingMethod {
    private Task task
    private List<TaskData> taskDataList
    private int taskDataSize
    private double a

    SmoothingMethod(Task task) {
        this.task = task
        taskDataList = task.data.sort{it.id}
        taskDataSize = taskDataList.size()
        this.a = 2 / (taskDataSize+1)
        a = 0.2 //TODO
    }

    double getA() { a }

    /** TODO */
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


        SmoothingMethodDataWrapper dataWrapper = new SmoothingMethodDataWrapper(taskData: smDataList, forecast: smDataList.removeLast(),
                sumAvgError1: sumAvgError1, sumAvgError2: sumAvgError2, epsilon1: sumAvgError1/taskDataSize, epsilon2: sumAvgError2/taskDataSize)


        return dataWrapper
    }

    Double getU0Var1() {
        return taskDataList.sum{it.value} / taskDataSize
    }

    Double getU0Var2() {
        return taskDataList.getAt(0).value
    }

    /** Расчте экспоненциально взвешенной средней Ut */
    private Double calcExpAvg(Double y, Double U) {
        return a * y + (1 - a) * U
    }

    /** Расчет средней относительной ошибки */
    private static Double calcAvgError(Double yFact, Double yCalc) {
        return Math.abs(yFact - yCalc) / yFact * 100
    }

}
