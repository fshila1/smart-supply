// src/app/products/models/product.model.ts
export interface Product {
    id: string;
    sku: string;
    name: string;
    description: string;
    unitPrice: number;
    weightKg: number;
    // category: string;
}