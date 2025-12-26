import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { loadSuppliers, loadSuppliersFailure, loadSuppliersSuccess, syncSupplier, syncSupplierFailure, syncSupplierSuccess } from "../actions/supplier.actions";
import { catchError, exhaustMap, map, of, switchMap } from "rxjs";
import { Supplier } from "../../models/supplier.model";
import { SupplierApiService } from "../../services/supplier.service";

@Injectable()
export class SupplierEffects {

    loadSuppliers$ = createEffect(() =>
        this.actions$.pipe(
            ofType(loadSuppliers),
            switchMap(() =>
                this.supplierApi.getSuppliers().pipe(
                    map((suppliers: Supplier[]) => loadSuppliersSuccess({ suppliers })),
                    catchError(err =>
                        of(loadSuppliersFailure({ error: err.message }))
                    )
                )
            )
        )
    );

    syncSupplier$ = createEffect(() =>
        this.actions$.pipe(
            ofType(syncSupplier),
            exhaustMap(({ supplierId }) =>
                this.supplierApi.syncSupplier(supplierId).pipe(
                    map(() => syncSupplierSuccess({ supplierId })),
                    catchError(err =>
                        of(syncSupplierFailure({ error: err.message }))
                    )
                )
            )
        )
    );

    constructor(
        private actions$: Actions,
        private supplierApi: SupplierApiService
    ) { }
}