import { Moment } from 'moment';
import { ISysCoupon } from 'app/shared/model//sys-coupon.model';
import { ISysCouponUser } from 'app/shared/model//sys-coupon-user.model';

export interface ISysCouponProduct {
    id?: number;
    residue?: number;
    createTime?: Moment;
    updateTime?: Moment;
    coupon?: ISysCoupon;
    couponUsers?: ISysCouponUser[];
}

export class SysCouponProduct implements ISysCouponProduct {
    constructor(
        public id?: number,
        public residue?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public coupon?: ISysCoupon,
        public couponUsers?: ISysCouponUser[]
    ) {}
}
