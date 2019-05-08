import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysCoupon } from 'app/shared/model/sys-coupon.model';

type EntityResponseType = HttpResponse<ISysCoupon>;
type EntityArrayResponseType = HttpResponse<ISysCoupon[]>;

@Injectable({ providedIn: 'root' })
export class SysCouponService {
    public resourceUrl = SERVER_API_URL + 'api/sys-coupons';

    constructor(protected http: HttpClient) {}

    create(sysCoupon: ISysCoupon): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCoupon);
        return this.http
            .post<ISysCoupon>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysCoupon: ISysCoupon): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCoupon);
        return this.http
            .put<ISysCoupon>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysCoupon>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysCoupon[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysCoupon: ISysCoupon): ISysCoupon {
        const copy: ISysCoupon = Object.assign({}, sysCoupon, {
            startTime: sysCoupon.startTime != null && sysCoupon.startTime.isValid() ? sysCoupon.startTime.toJSON() : null,
            endTime: sysCoupon.endTime != null && sysCoupon.endTime.isValid() ? sysCoupon.endTime.toJSON() : null,
            createTime: sysCoupon.createTime != null && sysCoupon.createTime.isValid() ? sysCoupon.createTime.toJSON() : null,
            updateTime: sysCoupon.updateTime != null && sysCoupon.updateTime.isValid() ? sysCoupon.updateTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.startTime = res.body.startTime != null ? moment(res.body.startTime) : null;
            res.body.endTime = res.body.endTime != null ? moment(res.body.endTime) : null;
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysCoupon: ISysCoupon) => {
                sysCoupon.startTime = sysCoupon.startTime != null ? moment(sysCoupon.startTime) : null;
                sysCoupon.endTime = sysCoupon.endTime != null ? moment(sysCoupon.endTime) : null;
                sysCoupon.createTime = sysCoupon.createTime != null ? moment(sysCoupon.createTime) : null;
                sysCoupon.updateTime = sysCoupon.updateTime != null ? moment(sysCoupon.updateTime) : null;
            });
        }
        return res;
    }
}
