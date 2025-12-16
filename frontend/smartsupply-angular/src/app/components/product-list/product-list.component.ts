// src/app/products/components/product-list/product-list.component.ts
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { MatTableModule } from '@angular/material/table';
import { Product } from '../../models/product.model';
import * as ProductActions from '../../store/actions/product.actions';
import * as fromProducts from '../../store/selectors/product.selectors';
import { CommonModule } from '@angular/common';

@Component({
    standalone: true,
    selector: 'app-product-list',
    templateUrl: './product-list.component.html',
    styleUrls: ['./product-list.component.scss'],
    imports: [CommonModule, MatTableModule]
})
export class ProductListComponent implements OnInit {
    products$!: Observable<Product[]>;
    loading$!: Observable<boolean>;
    // displayedColumns = ['id', 'name', 'price', 'stock', 'category'];
    displayedColumns = ['id', 'name', 'price', 'weight'];

    constructor(private store: Store) { }

    ngOnInit(): void {
        console.log('Dispatching loadProducts');
        this.store.dispatch(ProductActions.loadProducts());
        this.products$ = this.store.select(fromProducts.selectAllProducts);
        this.loading$ = this.store.select(fromProducts.selectProductLoading);
    }
}