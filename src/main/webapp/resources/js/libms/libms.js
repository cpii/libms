/*var libmsModule = angular.module('libmsModule', ['ngResource']);

libmsModule.controller('BookCtrl', function($scope, Book) {
	$scope.books = Book.query(function(data) {		
	});
	
	$scope.addBook = function() {
		$scope.book = new Book();
		$scope.book.author = 'Kamal Mansoor';
		$scope.book.title = 'Angular Rocks';
		$scope.book.cost = '15.99';
		Book.save($scope.book, function(book) {
			console.log('New Book created with ID: ' + book.id);
			$scope.books.push(book);
		})
		$scope.book.$save(function(book) {
			console.log('New Book created with ID: ' + book.id);
			$scope.books.push(book);
		});
	}
	
	$scope.editBook = function(index) {
		var book = $scope.books[index];
		$scope.book = Book.get({id: book.id}, function() {
			$scope.book.cost = 1.99;
			$scope.book._id = $scope.book.id;
			$scope.book.$update(function(bk) {
				console.log(book.id + ' new cost: ' + bk.cost);
				$scope.books.splice(index);
				$scope.books.splice(index, 0, bk);
			})
		});		
	}
	
	$scope.deleteBook = function(index) {
		var bk = $scope.books[index];
		$scope.book = new Book();
		$scope.book.id = bk.id;
		$scope.book.$delete(function() {
			console.log("deleting... " );
			$scope.books.splice(index, 1);
		});
	}
});

libmsModule.factory('Book', function($resource) {
	return $resource('/libms/books/:id', {id: '@id'}, {
		update: {
			method: 'PUT'
		}
	});
});*/