import { createReducer, on } from "@ngrx/store";
import { Supplier } from "../../models/supplier.model";
import { loadSuppliers, loadSuppliersFailure, loadSuppliersSuccess, syncSupplier, syncSupplierFailure, syncSupplierSuccess } from "../actions/supplier.actions";

export interface SupplierState {
    suppliers: Supplier[];
    loading: boolean;
    syncingSupplierId?: string;
    error?: string;
}

export const initialSupplierState: SupplierState = {
    suppliers: [],
    loading: false
};

export const supplierReducer = createReducer(
    initialSupplierState,

    on(loadSuppliers, state => ({
        ...state,
        loading: true
    })),

    on(loadSuppliersSuccess, (state, { suppliers }) => ({
        ...state,
        suppliers,
        loading: false
    })),

    on(loadSuppliersFailure, (state, { error }) => ({
        ...state,
        loading: false,
        error
    })),

    on(syncSupplier, (state, { supplierId }) => ({
        ...state,
        syncingSupplierId: supplierId
    })),

    on(syncSupplierSuccess, state => ({
        ...state,
        syncingSupplierId: undefined
    })),

    on(syncSupplierFailure, (state, { error }) => ({
        ...state,
        syncingSupplierId: undefined,
        error
    }))
);