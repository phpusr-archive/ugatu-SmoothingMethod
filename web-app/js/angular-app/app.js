'use strict';

/* App Module */

var app = angular.module('app', ['ngRoute', 'controllers']);

/**
 * Класс для TaskData
 */
function Data(title, value) {
    this.title = title;
    this.value = value;
}