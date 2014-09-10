(function() {
  var module = angular.module('photoBooth', ['ngCordova', 'ngAnimate']);

  module.controller('photoBoothController', function($scope, $cordovaCamera, sharePhoto) {
    $scope.sharePhoto = function(uri) {
      sharePhoto(uri);
    };

    $scope.getPhoto = function() {
      $cordovaCamera.getPicture().then(function(imageData) {
          $scope.imageData = imageData;
        }, function() {
          alert(arguments);
        });
    };
  });

  module.service('sharePhoto', function() {
    return function(pictureURI) {
      alert(pictureURI);
      cordova.exec(
        // success, do nothing
        function() { },
        // failure
        function(arg) {
          alert('failure');
          alert(arg);
        },
        'Share',
        'share',
        [pictureURI]
      );
    };
  });

  document.addEventListener('deviceready', function() {
    angular.bootstrap( document, ['photoBooth']);
  });
})();
