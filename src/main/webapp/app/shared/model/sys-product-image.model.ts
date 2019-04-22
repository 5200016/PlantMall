import { Moment } from 'moment';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysProductImage {
    id?: number;
    url?: string;
    createTime?: Moment;
    updateTime?: Moment;
    product?: ISysProduct;
}

export class SysProductImage implements ISysProductImage {
    constructor(
        public id?: number,
        public url?: string,
        public createTime?: Moment,
        public updateTime?: Moment,
        public product?: ISysProduct
    ) {}
}
