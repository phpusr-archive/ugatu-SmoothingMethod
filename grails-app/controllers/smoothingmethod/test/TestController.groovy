package smoothingmethod.test

import grails.converters.JSON
import grails.converters.XML
import smoothingmethod.method.Task
import smoothingmethod.method.TaskData

import java.text.SimpleDateFormat

/**
 * Контроллер для тестирования всяких штук
 */
class TestController {

    def index() {
        //TODO Вывод всех action
        render 'TODO Вывод всех action'
    }

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

    /**
     * Вывод курса валют
     * http://www.thecoderscorner.com/team-blog/groovy-and-grails/15-reading-and-writing-xml-in-groovy#.UrcBg_RdU2w
     * http://toster.ru/q/7088
     */
    def currencyRates() {
        def slurper = new XmlSlurper()
        def htmlParser = slurper.parse('http://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml')
        def currencyRates = htmlParser.Cube.Cube

        importIntoTask(currencyRates)
        renderHtml(currencyRates)
    }

    /** Импорт курса валют в качестве Задачи */
    protected static void importIntoTask(def currencyRates) {
        def task = new Task(name: 'Курсы валют', titleTitle: 'Дни', titleValue: 'Курсы валют', titleForecast: 'Прогноз на 20-е', smoothingParameter: 0.2).save(flush: true)
        def df = new SimpleDateFormat('yyyy-MM-dd')
        def dfRus = new SimpleDateFormat('dd.MM.yyyy')

        currencyRates.eachWithIndex { crDate, i ->
            if (i < 10) {
                Date date = df.parse(crDate['@time'] as String)
                String dateString = dfRus.format(date)
                def rate = crDate.Cube.find { cr -> cr['@currency'] == 'RUB' }['@rate']
                Double rateDouble = Double.parseDouble(rate as String)

                new TaskData(title: dateString, value: rateDouble, task: task).save(flush: true)

                println ">> $dateString : $rateDouble"
            }
        }
    }

    /** Вывод курса валют (html) */
    protected void renderHtml(def currencyRates) {
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
