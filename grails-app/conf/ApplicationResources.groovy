modules = {
    application {
        resource url:'js/application.js'
    }

    angularApp {
        dependsOn 'angular', 'angular-route'

        resource url:'js/angular-app/app.js'
//        resource url:'js/angular-app/services.js'
//        resource url:'js/angular-app/controllers.js'
//        resource url:'js/angular-app/filters.js'
//        resource url:'js/angular-app/directives.js'
    }
}