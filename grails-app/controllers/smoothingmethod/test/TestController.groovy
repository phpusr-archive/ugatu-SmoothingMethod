package smoothingmethod.test

import grails.converters.JSON
import grails.converters.XML
import smoothingmethod.method.Task

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

    /** Вывод курса валют */
    def currencyRates() {
        def slurper = new XmlSlurper()
        def htmlParser = slurper.parse('http://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml')
        def currencyRates = htmlParser.Cube.Cube

        renderHtml(currencyRates)
    }

    /** Вывод курса валют (html) */
    protected def renderHtml(def currencyRates) {
        render {
            html {
                table(border: '1px') {
                    //Вывод заголовков
                    tr {
                        def crDate = currencyRates.getAt(0)
                        th 'Date'
                        crDate.Cube.each { cr->
                            th cr['@currency']
                        }
                    }
                    //Вывод данных
                    currencyRates.each { crDate ->
                        tr { td crDate['@time']
                            crDate.Cube.each { cr ->
                                td "${cr['@rate']}"
                            }
                        }
                    }
                }
            }
        }
    }

}
