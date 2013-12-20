package util

import grails.util.Environment
import grails.util.Metadata

/**
 * @author phpusr
 * Date: 28.10.12
 * Time: 18:19
 */

/**
 * Класс для работы с properties-файлами
 */
class PropUtil {

    /** Возвращает путь к папке с программой */
    static String getProgPath() {
        return System.getenv('TABEL_HOME')
    }

    /** Возвращает application.properties */
    static Metadata getAppProp() {
        return Metadata.current as Metadata
    }

    /** Чтение properties-файлов */
    static ConfigObject getConfig(String fileName) {
        def env = Environment.current.name
        return new ConfigSlurper(env).parse(new File(fileName).text)
    }

    /** Возвращает файл-конфигурации по умолчанию */
    static ConfigObject getDefaultConfig() {
        def path = progPath + '/' + appProp.'app.config.path'
        def fileName = appProp.'app.config.filename'
        def configName = "$path/$fileName"
        return getConfig(configName)
    }

}
