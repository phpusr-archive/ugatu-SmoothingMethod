package smoothingmethod.method

/**
 * @author phpusr
 * Date: 20.12.13
 * Time: 18:38
 */

/**
 * TODO
 */
class SmoothingMethodDataWrapper {
    List<SmoothingMethodData> taskData
    SmoothingMethodData forecast
    Double a

    Double sumAvgError1
    Double sumAvgError2
    Double epsilon1
    Double epsilon2

    /** Удовлитворительна ли точность прогноза */
    boolean forecastAccuracy
}
