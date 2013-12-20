package smoothingmethod.method

/**
 * Данные для задачи
 */
class TaskData {

    static constraints = {
        task()
        title blank: false
        value()
    }

    /** Задача */
    Task task
    /** Заголовок */
    String title
    /** Значение */
    Double value

}
