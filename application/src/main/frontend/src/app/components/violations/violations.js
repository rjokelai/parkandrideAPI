// Copyright © 2015 HSL <https://www.hsl.fi>
// This program is dual-licensed under the EUPL v1.2 and AGPLv3 licenses.

(function() {
    var m = angular.module('parkandride.components.violations', [
        'parkandride.i18n'
    ]);

    m.directive('violations', violations);
    m.controller('ViolationsCtrl', ViolationsCtrl);
    m.factory('violationsManager', violationsManager);

    function violations(violationsManager) {
        return {
            restrict: 'E',
            scope: {
                context: '@'
            },
            bindToController: true,
            controller: 'ViolationsCtrl',
            controllerAs: 'ctrl',
            templateUrl: 'components/violations/violations.tpl.html'
        };
    }

    function ViolationsCtrl(violationsManager, $element) {
        var vm = this;
        vm.model = violationsManager.initContext(vm.context, onViolationsUpdated);
        vm.hasViolations = hasViolations;
        vm.getLabel = getLabel;

        function hasViolations() {
            return violationsManager.hasViolations(vm.context);
        }

        function getLabel(violation) {
            return vm.context + (violation.path ? '.' + violation.path : '') + '.label';
        }

        function onViolationsUpdated() {
            $element[0].scrollIntoView();
        }
    }

    function violationsManager($log) {
        var model = {};

        var api = {};
        api.setViolations = setViolations;
        api.hasViolations = hasViolations;
        api.initContext = initContext;

        function initContext(context, onViolationsUpdated) {
            model[context] = {
                violations: [],
                onViolationsUpdated: onViolationsUpdated
            };
            //$log.debug("initialized context [", context, "]", model[context]);
            return model[context];
        }

        function setViolations(context, violations) {
            function filterDuplicates(violations) {
                var filtered = [];

                var duplicates = {};
                for (var i=0; i < violations.length; i++) {
                    var violation = violations[i];
                    violation.path = violation.path.replace(/\[\d+\]/, ""); // same violations in different indexes are considered the same
                    var violationKey = violation.path + "/" + violation.type;
                    if (!duplicates[violationKey]) {
                        duplicates[violationKey] = true;
                        filtered.push(violation);
                    }
                }

                return filtered;
            }

            angular.copy(filterDuplicates(violations), model[context].violations);
            //$log.debug("set violations [", context, "]", model[context].violations);
            model[context].onViolationsUpdated();
        }

        function hasViolations(context) {
            return !_.isEmpty(model[context].violations);
        }

        return api;
    }

})();
