import { Moment } from 'moment';
import { ISysUser } from 'app/shared/model//sys-user.model';
import { ISysShoppingProduct } from 'app/shared/model//sys-shopping-product.model';

export interface ISysShoppingCar {
    id?: number;
    type?: number;
    createTime?: Moment;
    updateTime?: Moment;
    user?: ISysUser;
    shoppingProducts?: ISysShoppingProduct[];
}

export class SysShoppingCar implements ISysShoppingCar {
    constructor(
        public id?: number,
        public type?: number,
        public createTime?: Moment,
        public updateTime?: Moment,
        public user?: ISysUser,
        public shoppingProducts?: ISysShoppingProduct[]
    ) {}
}
