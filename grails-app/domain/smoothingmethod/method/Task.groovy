package smoothingmethod.method

/**
 * Задача
 */
class Task {

    static constraints = {
        name blank: false
        titleTitle blank: false
        titleValue blank: false
        titleForecast blank: false
    }

    /** Данные задачи */
    static hasMany = [data: TaskData]

    /** Название задачи */
    String name
    /** Заголовок для заголовков данных */
    String titleTitle
    /** Заголовок для значений данных */
    String titleValue
    /** Заголовок для прогноза */
    String titleForecast

}
