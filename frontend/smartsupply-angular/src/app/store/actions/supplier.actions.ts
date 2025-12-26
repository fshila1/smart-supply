import { createAction, props } from '@ngrx/store';
import { Supplier } from '../../models/supplier.model';

export const loadSuppliers = createAction('[Supplier] Load');
export const loadSuppliersSuccess = createAction(
    '[Supplier] Load Success',
    props<{ suppliers: Supplier[] }>()
);
export const loadSuppliersFailure = createAction(
    '[Supplier] Load Failure',
    props<{ error: string }>()
);

export const syncSupplier = createAction(
    '[Supplier] Sync',
    props<{ supplierId: string }>()
);

export const syncSupplierSuccess = createAction(
    '[Supplier] Sync Success',
    props<{ supplierId: string }>()
);

export const syncSupplierFailure = createAction(
    '[Supplier] Sync Failure',
    props<{ error: string }>()
);