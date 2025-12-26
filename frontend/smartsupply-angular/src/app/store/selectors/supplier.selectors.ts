import { createFeatureSelector, createSelector } from "@ngrx/store";
import { SupplierState } from "../reducers/supplier.reducer";

export const selectSupplierState =
    createFeatureSelector<SupplierState>('suppliers');

export const selectAllSuppliers = createSelector(
    selectSupplierState,
    state => state.suppliers
);

export const selectLoading = createSelector(
    selectSupplierState,
    state => state.loading
);

export const selectSyncingSupplierId = createSelector(
    selectSupplierState,
    state => state.syncingSupplierId
);