import { Moment } from 'moment';
import { ISysProduct } from 'app/shared/model//sys-product.model';

export interface ISysClassify {
    id?: number;
    name?: string;
    createTime?: Moment;
    updateTime?: Moment;
    products?: ISysProduct[];
}

export class SysClassify implements ISysClassify {
    constructor(
        public id?: number,
        public name?: string,
        public createTime?: Moment,
        public updateTime?: Moment,
        public products?: ISysProduct[]
    ) {}
}
