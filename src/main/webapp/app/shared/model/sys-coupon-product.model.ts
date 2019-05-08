import { Moment } from 'moment';
import { ISysCoupon } from 'app/shared/model//sys-coupon.model';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysCouponProduct {
    id?: number;
    residue?: number;
    createTime?: Moment;
    updateTime?: Moment;
    coupon?: ISysCoupon;
    product?: ISysProduct;
}

export class SysCouponProduct implements ISysCouponProduct {
    constructor(
        public id?: number,
        public residue?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public coupon?: ISysCoupon,
        public product?: ISysProduct
    ) {}
}
