import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Supplier } from "../models/supplier.model";

@Injectable({ providedIn: 'root' })
export class SupplierApiService {
    private apiUrl = 'http://localhost:8085/api/suppliers';

    constructor(private http: HttpClient) { }

    getSuppliers() {
        return this.http.get<Supplier[]>(this.apiUrl);
    }

    syncSupplier(supplierId: string) {
        return this.http.post<void>(
            `${this.apiUrl}/${supplierId}/sync`,
            {}
        );
    }
}