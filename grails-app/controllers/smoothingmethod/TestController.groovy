package smoothingmethod

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

}
