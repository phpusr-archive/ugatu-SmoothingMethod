'use strict';

/* App Module */

var app = angular.module('app', ['ngRoute', 'controllers']);

/**
 * TODO Структура данных
 */
function Data(title, value) {
    this.title = title;
    this.value = value;
}