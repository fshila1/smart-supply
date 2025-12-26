export interface Supplier {
    id: string;
    code: string;
    name: string;
    status: SupplierStatus;
    lastSyncAt?: string;
    syncStatus?: SyncStatus;
}

export type SupplierStatus =
    | 'ACTIVE'
    | 'INACTIVE';

export type SyncStatus =
    | 'NOT_SYNCED'
    | 'IN_PROGRESS'
    | 'SUCCESS'
    | 'FAILED';