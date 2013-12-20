import grails.util.GrailsUtil
import smoothingmethod.Task
import util.PropUtil

class BootStrap {

    def init = { servletContext ->
        def ret = 'nothing' //nothing, lite, all
        if (GrailsUtil.developmentEnv) {
            if (PropUtil.getConfig('grails-app/conf/DataSource.groovy').dataSource.dbCreate == 'validate') {ret = 'nothing'} else {ret = 'all'}
        }

        if (ret == 'nothing') return

        new Task(name: 'Test').save(failOnError: true)

    }
    def destroy = {
    }
}
