import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysCoupon } from 'app/shared/model//sys-coupon.model';

export interface ISysCouponUser {
    id?: number;
    useStatus?: number;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    coupon?: ISysCoupon;
}

export class SysCouponUser implements ISysCouponUser {
    constructor(
        public id?: number,
        public useStatus?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public coupon?: ISysCoupon
    ) {}
}
