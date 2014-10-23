'use strict';

module.exports = function(spec) {
    var that = require('../base')(spec);

    spec.view = $('.wdFacilityEditView');
    spec.name = element(by.model('editCtrl.facility.name'));
    spec.map = $('.facility-map .ol-viewport');
    spec.saveButton = element.all(by.css('.wdSave')).first();
    spec.aliases = $('.wdAliases .tags');
    spec.capacityTypes = element.all(by.css(".wdCapacityType"));

    that.get = function (id) {
        if (id) {
            browser.get('/#/facilities/edit/' + id);
        } else {
            browser.get('/#/facilities/create');
        }
    };

    that.getName = function () {
        return spec.name.getAttribute('value');
    };

    that.setName = function (name) {
        return spec.name.sendKeys(name);
    };

    that.drawBorder = function (topLeft, w, h) {
        spec.ptor.actions()
            .mouseMove(spec.map, topLeft).click()
            .mouseMove(spec.map, {x: topLeft.x, y: topLeft.y + h}).click()
            .mouseMove(spec.map, {x: topLeft.x + w, y: topLeft.y + h}).click()
            .mouseMove(spec.map, {x: topLeft.x + w, y: topLeft.y}).click()
            .mouseMove(spec.map, topLeft).click()
            .perform();
    };

    that.save = function () {
        spec.saveButton.click();
    };

    that.addAlias = function (alias) {
        spec.aliases.click();
        var tagsElement = browser.driver.switchTo().activeElement();
        tagsElement.sendKeys(alias);
        tagsElement.sendKeys(protractor.Key.ENTER);
    };

    that.setCapacities = function (capacities) {
        for (var capacityType in capacities) {
            var capacity = capacities[capacityType];
            for (var prop in capacity) {
                element(by.css('.wd' + capacityType + prop)).sendKeys(capacity[prop]);
            }
        }
    };

    that.getCapacityTypes = function() {
      return spec.capacityTypes.filter(function(el) { return el.isDisplayed(); }).getText();
    };

    return that;
};