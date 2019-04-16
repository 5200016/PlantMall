import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysCollection {
    id?: number;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    product?: ISysProduct;
}

export class SysCollection implements ISysCollection {
    constructor(
        public id?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public product?: ISysProduct
    ) {}
}
