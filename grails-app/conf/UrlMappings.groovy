class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/" (controller: 'task')
        "/controllers" (view: '/index')
        "500" (view: '/error')
	}
}
