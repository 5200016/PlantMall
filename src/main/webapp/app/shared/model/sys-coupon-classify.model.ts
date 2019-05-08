import { Moment } from 'moment';
import { ISysCoupon } from 'app/shared/model//sys-coupon.model';
import { ISysClassify } from 'app/shared/model//sys-classify.model';

export interface ISysCouponClassify {
    id?: number;
    createTime?: Moment;
    updateTime?: Moment;
    coupon?: ISysCoupon;
    classify?: ISysClassify;
}

export class SysCouponClassify implements ISysCouponClassify {
    constructor(
        public id?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public coupon?: ISysCoupon,
        public classify?: ISysClassify
    ) {}
}
