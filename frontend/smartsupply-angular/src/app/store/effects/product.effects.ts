// src/app/products/store/effects/product.effects.ts
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, mergeMap, tap } from 'rxjs/operators';
import * as ProductActions from '../actions/product.actions';
import { ProductService } from '../../services/product.service';

@Injectable()
export class ProductEffects {
    loadProducts$;

    constructor(private actions$: Actions, private productService: ProductService) {
        this.loadProducts$ = createEffect(() =>
            this.actions$.pipe(
                ofType(ProductActions.loadProducts),
                mergeMap(() =>
                    this.productService.getAllProducts().pipe(
                        map((products) => {
                            console.log(products);
                            return ProductActions.loadProductsSuccess({ products })
                        }),
                        catchError(error => of(ProductActions.loadProductsFailure({ error })))
                    )
                )
            )
        );
    }
}