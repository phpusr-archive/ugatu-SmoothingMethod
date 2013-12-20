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
    private double a

    SmoothingMethod(Task task) {
        this.task = task
        taskDataList = task.data.sort{it.id}
        this.a = 2 / (taskDataList.size()+1)
        a = 0.2 //TODO
    }

    double getA() { a }

    /** TODO */
    List<SmoothingMethod> calc() {
        List<SmoothingMethod> smDataList = []

        Double UVar1 = getU0Var1()
        Double UVar2 = getU0Var2()

        taskDataList << new TaskData(title: 'Прогноз на Ноябрь', value: taskDataList.last().value) //Иметь в виду, что влияет на size()
        taskDataList.each { taskData->
            SmoothingMethodData smData = new SmoothingMethodData(title: taskData.title, value: taskData.value)
            smData.expAvg1 = UVar1
            smData.expAvg2 = UVar2
            smData.avgError1 = calcAvgError(taskData.value, UVar1)
            smData.avgError2 = calcAvgError(taskData.value, UVar2)
            UVar1 = calcExpAvg(taskData.value, UVar1)
            UVar2 = calcExpAvg(taskData.value, UVar2)

            smDataList << smData
        }
        taskDataList.remove(taskDataList.size()-1)

        return smDataList
    }

    Double getU0Var1() {
        return taskDataList.sum{it.value} / taskDataList.size()
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
        println "yFact: $yFact, yCalc: $yCalc"
        return Math.abs(yFact - yCalc) / yFact * 100
    }

}
