import { Component, ChangeDetectionStrategy, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import { Supplier } from '../../models/supplier.model';
import {
    loadSuppliers,
    syncSupplier
} from '../../store/actions/supplier.actions';
import {
    selectAllSuppliers,
    selectLoading,
    selectSyncingSupplierId
} from '../../store/selectors/supplier.selectors';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-supplier',
    templateUrl: './supplier-list.component.html',
    styleUrl: './supplier-list.component.scss',
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [CommonModule]
})
export class SupplierComponent implements OnInit {

    suppliers$: Observable<Supplier[]>;
    loading$: Observable<boolean>;
    syncingSupplierId$: Observable<string | undefined>;

    constructor(private store: Store) {
        this.suppliers$ = this.store.select(selectAllSuppliers);
        this.loading$ = this.store.select(selectLoading);
        this.syncingSupplierId$ = this.store.select(selectSyncingSupplierId);
    }

    ngOnInit(): void {
        this.store.dispatch(loadSuppliers());
    }

    onSyncSupplier(supplierId: string): void {
        this.store.dispatch(syncSupplier({ supplierId }));
    }

    trackBySupplierId(_: number, supplier: Supplier): string {
        return supplier.id;
    }
}