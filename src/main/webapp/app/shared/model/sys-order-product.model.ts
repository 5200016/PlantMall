import { Moment } from 'moment';
import { ISysOrder } from 'app/shared/model//sys-order.model';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysOrderProduct {
    id?: number;
    productStatus?: number;
    createTime?: Moment;
    updateTime?: Moment;
    order?: ISysOrder;
    product?: ISysProduct;
}

export class SysOrderProduct implements ISysOrderProduct {
    constructor(
        public id?: number,
        public productStatus?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public order?: ISysOrder,
        public product?: ISysProduct
    ) {}
}
