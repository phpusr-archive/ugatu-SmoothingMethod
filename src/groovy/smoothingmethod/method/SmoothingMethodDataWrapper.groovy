package smoothingmethod.method

/**
 * @author phpusr
 * Date: 20.12.13
 * Time: 18:38
 */

/**
 * Данные для метода: Экспоненциального сглаживания
 */
class SmoothingMethodDataWrapper {
    /** Задача */
    Task task
    /** Данные задачи */
    List<SmoothingMethodData> taskData
    /** Прогноз */
    SmoothingMethodData forecast
    /** Параметр сглаживания */
    Double a

    /** Сумма относительных ошибок v1 */
    Double sumRelativeError1
    /** Сумма относительных ошибок v2 */
    Double sumRelativeError2
    /** Средняя относительная ошибка v1 */
    Double epsilon1
    /** Средняя относительная ошибка v2 */
    Double epsilon2

    /** Удовлитворительна ли точность прогноза */
    boolean forecastAccuracy
}
