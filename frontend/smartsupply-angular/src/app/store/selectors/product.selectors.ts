// src/app/products/store/selectors/product.selectors.ts
import { createFeatureSelector, createSelector } from '@ngrx/store';
import { ProductState } from '../reducers/product.reducer';

export const selectProductState = createFeatureSelector<ProductState>('products');

// export const selectAllProducts = createSelector(
//     selectProductState,
//     (state: ProductState) => state.products
// );

export const selectAllProducts = createSelector(
    selectProductState,
    (state: ProductState | undefined) => state?.products ?? []
);

export const selectProductLoading = createSelector(
    selectProductState,
    (state: ProductState) => state.loading
);