package smoothingmethod.method

/**
 * @author phpusr
 * Date: 20.12.13
 * Time: 16:46
 */

/**
 * Данные для таблицы метода: Экспоненциального сглаживания
 */
class SmoothingMethodData {
    /** Заголовок */
    String title
    /** Значение */
    Double value
    /** Экспоненциальная взвешенная средняя v1 (Ut) */
    Double expAvg1
    /** Экспоненциальная взвешенная средняя v2 (Ut) */
    Double expAvg2
    /** Относительная ошибка v1 */
    Double relativeError1
    /** Относительная ошибка v2 */
    Double relativeError2
}
