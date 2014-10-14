'use strict';

var _ = require('lodash');

var Pages = require('../pages/pages.js');
var FacilityFixture = require('../fixtures/fixtures.js').FacilityFixture;

describe('Basic flow', function() {
    var menu = new Pages.Menu();
    var indexPage = new Pages.IndexPage();

    var facilityListPage = new Pages.FacilityListPage();
    var facilityEditPage = new Pages.FacilityEditPage();
    var facilityViewPage = new Pages.FacilityViewPage();

    var hubListPage = new Pages.HubListPage();
    var hubEditPage = new Pages.HubEditPage();
    var hubViewPage = new Pages.HubViewPage();

    function newFacilityName() {
        return 'Test Facility ' + new Date().getTime();
    }

    function newHubName() {
        return 'Test Hub ' + new Date().getTime();
    }

    var facility1 = new FacilityFixture({
        capacities: {
            "CAR": {"built": 10, "unavailable": 1},
            "BICYCLE": {"built": 20, "unavailable": 2},
            "PARK_AND_RIDE": {"built": 30, "unavailable": 3},
            "DISABLED": {"built": 40, "unavailable": 4},
            "MOTORCYCLE": {"built": 50, "unavailable": 5},
            "ELECTRIC_CAR": {"built": 60, "unavailable": 6}
        },
        aliases: ["fac1", "facility1"],
        border: {
            offset: {x: 90, y: 90},
            w: 60,
            h: 60
        }
    });

    var facility2 = new FacilityFixture({
        capacities: {
            "CAR": {"built": 10, "unavailable": 1}
        },
        aliases: ["fac2"],
        border: {
            offset: {x: 180, y: 180},
            w: 60,
            h: 60
        }
    });

    it('Go to facility create', function() {
        indexPage.get();
        expect(facilityListPage.isDisplayed()).toBe(true);
        facilityListPage.toCreateView();
        expect(facilityEditPage.isDisplayed()).toBe(true);
    });

    it('Create facility 1', function () {
        facility1.name = newFacilityName();
        facilityEditPage.setName(facility1.name);
        expect(facilityEditPage.getName()).toEqual(facility1.name);

        facilityEditPage.drawBorder(facility1.border.offset, facility1.border.w, facility1.border.h);
        facilityEditPage.addAlias(facility1.aliases[0]);
        facilityEditPage.addAlias(facility1.aliases[1]);
        facilityEditPage.setCapacities(facility1.capacities);

        facilityEditPage.save();
        expect(facilityViewPage.isDisplayed()).toBe(true);
        expect(facilityViewPage.getName()).toBe(facility1.name);
        facilityViewPage.assertAliases(facility1.aliases);
        facilityViewPage.assertCapacities(facility1.capacities);
    });

    it('Return to list and go to facility create', function() {
        facilityViewPage.toListView();
        expect(facilityListPage.isDisplayed()).toBe(true);
        facilityListPage.toCreateView();
        expect(facilityEditPage.isDisplayed()).toBe(true);
    });

    it('Create facility 2', function() {
        facility2.name = newFacilityName();
        facilityEditPage.setName(facility2.name);
        expect(facilityEditPage.getName()).toEqual(facility2.name);

        facilityEditPage.drawBorder(facility2.border.offset, facility2.border.w, facility2.border.h);
        facilityEditPage.addAlias(facility2.aliases[0]);
        facilityEditPage.setCapacities(facility2.capacities);

        facilityEditPage.save();
        expect(facilityViewPage.isDisplayed()).toBe(true);
        expect(facilityViewPage.getName()).toBe(facility2.name);
        facilityViewPage.assertAliases(facility2.aliases);
        facilityViewPage.assertCapacities(facility2.capacities);
    });

    it('Go to create hub via hub list', function() {
        menu.toHubs();
        expect(hubListPage.isDisplayed()).toBe(true);
        hubListPage.toCreateView();
        expect(hubEditPage.isDisplayed()).toBe(true);
    });

    it('Create hub', function() {
        var hubName = newHubName();
        hubEditPage.setName(hubName);
        hubEditPage.toggleFacility(facility1);
        hubEditPage.toggleFacility(facility2);
        hubEditPage.setLocation({x: 165, y: 165});

        hubEditPage.save();
        expect(hubViewPage.isDisplayed()).toBe(true);
        expect(hubViewPage.getName()).toBe(hubName);
        hubViewPage.assertCapacities([facility1, facility2]);
    });
});