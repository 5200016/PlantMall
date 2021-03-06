import { Moment } from 'moment';
import { ISysCouponUser } from 'app/shared/model//sys-coupon-user.model';
import { ISysCouponProduct } from 'app/shared/model//sys-coupon-product.model';
import { ISysCouponClassify } from 'app/shared/model//sys-coupon-classify.model';

export interface ISysCoupon {
    id?: number;
    type?: number;
    name?: string;
    value?: number;
    quantity?: number;
    getNumber?: number;
    limit?: number;
    startTime?: Moment;
    endTime?: Moment;
    description?: any;
    moneyOff?: number;
    range?: number;
    createTime?: Moment;
    updateTime?: Moment;
    couponUsers?: ISysCouponUser[];
    couponProducts?: ISysCouponProduct[];
    couponClassifies?: ISysCouponClassify[];
}

export class SysCoupon implements ISysCoupon {
    constructor(
        public id?: number,
        public type?: number,
        public name?: string,
        public value?: number,
        public quantity?: number,
        public getNumber?: number,
        public limit?: number,
        public startTime?: Moment,
        public endTime?: Moment,
        public description?: any,
        public moneyOff?: number,
        public range?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public couponUsers?: ISysCouponUser[],
        public couponProducts?: ISysCouponProduct[],
        public couponClassifies?: ISysCouponClassify[]
    ) {}
}
