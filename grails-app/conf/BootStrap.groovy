import grails.util.GrailsUtil
import smoothingmethod.method.Task
import smoothingmethod.method.TaskData
import util.PropUtil

class BootStrap {

    def init = { servletContext ->
        def ret = 'nothing' //nothing, lite, all
        if (GrailsUtil.developmentEnv) {
            if (PropUtil.getConfig('grails-app/conf/DataSource.groovy').dataSource.dbCreate == 'validate') {ret = 'nothing'} else {ret = 'all'}
        }

        if (ret == 'nothing') return

        //Задача: Уровень безработицы по месяцам
        def task1 = new Task(name: 'Уровень безработицы', titleTitle: 'Месяцы', titleValue: 'Уровень безработицы', titleForecast: 'Прогноз на ноябрь', smoothingParameter: 0.2).save(failOnError: true)
        new TaskData(task: task1, title: 'Январь', value: 2.99).save(failOnError: true)
        new TaskData(task: task1, title: 'Февраль', value: 2.66).save(failOnError: true)
        new TaskData(task: task1, title: 'Март', value: 2.63).save(failOnError: true)
        new TaskData(task: task1, title: 'Апрель', value: 2.56).save(failOnError: true)
        new TaskData(task: task1, title: 'Май', value: 2.40).save(failOnError: true)
        new TaskData(task: task1, title: 'Июнь', value: 2.22).save(failOnError: true)
        new TaskData(task: task1, title: 'Июль', value: 1.97).save(failOnError: true)
        new TaskData(task: task1, title: 'Август', value: 1.72).save(failOnError: true)
        new TaskData(task: task1, title: 'Сентябрь', value: 1.56).save(failOnError: true)
        new TaskData(task: task1, title: 'Октябрь', value: 1.42).save(failOnError: true)

    }
    def destroy = {
    }
}
