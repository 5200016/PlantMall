import { Moment } from 'moment';
import { ISysProduct } from 'app/shared/model//sys-product.model';
import { ISysUser } from 'app/shared/model//sys-user.model';

export interface ISysReview {
    id?: number;
    content?: any;
    level?: number;
    createTime?: Moment;
    updateTime?: Moment;
    product?: ISysProduct;
    user?: ISysUser;
}

export class SysReview implements ISysReview {
    constructor(
        public id?: number,
        public content?: any,
        public level?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public product?: ISysProduct,
        public user?: ISysUser
    ) {}
}
