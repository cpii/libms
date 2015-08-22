<div id="home-container" class="home" data-ng-app="libmsModule">
	
	<div data-ng-controller="BookCtrl">
	<a href="#" data-ng-click="addBook()">Add Book</a>
	   <ul>
            <li data-ng-repeat="book in books" data-ng-click="deleteBook($index)">{{book.id}} {{book.author}} {{book.title}} {{book.cost}}</li>
        </ul>
	</div>
</div>
