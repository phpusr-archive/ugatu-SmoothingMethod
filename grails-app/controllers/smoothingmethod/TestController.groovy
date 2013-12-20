package smoothingmethod

import grails.converters.JSON
import grails.converters.XML

/**
 * Контроллер для тестирования всяких штук
 */
class TestController {

    /** Проверка получения массива параметров */
    def checkParams() {
        println params.val
    }

    /** Тест функции respond */
    def testRespond() {
        def taskList = Task.list()
        def valStr = 'valStr'
        def list = ['valStr']

        respond taskList, model: [test: 'test']
        //respond taskList.get(0)
        //respond TaskData.get(1)
        //respond list
    }

    /** Тест выводимого формата */
    def testFormat() {
        def task = Task.get(1)

        XML.use('deep') //Включение глубокого вывода (действует на все запросы)
        JSON.use(null) //Выключение глубокго вывода (действует на все запросы)

        respond task
        //return

        //Указание формата вывода
        withFormat {
            html task: task
            text { render task }
            json { render task as JSON }
            //xml { render task as XML }
        }
    }

}
