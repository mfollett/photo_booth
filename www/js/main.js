(function() {
  var module = angular.module('photoBooth', ['ngCordova', 'ngAnimate']);

  module.controller('photoBoothController', function($scope, $cordovaCamera) {
    $scope.getPhoto = function() {
      $cordovaCamera.getPicture().then(function(imageData) {
        $scope.imageData = imageData;
      }, function() {
        alert(arguments);
      });
    };
  });

  document.addEventListener('deviceready', function() {
    angular.bootstrap( document, ['photoBooth']);
  });
})();
