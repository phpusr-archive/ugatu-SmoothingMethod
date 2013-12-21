'use strict';

/* App Module */

var app = angular.module('app', ['ngRoute', 'controllers']);

/**
 * TODO Структура данных
 */
function Data(title, val) {
    this.title = title;
    this.val = val;
}