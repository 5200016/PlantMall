import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysCouponClassify } from 'app/shared/model/sys-coupon-classify.model';

type EntityResponseType = HttpResponse<ISysCouponClassify>;
type EntityArrayResponseType = HttpResponse<ISysCouponClassify[]>;

@Injectable({ providedIn: 'root' })
export class SysCouponClassifyService {
    public resourceUrl = SERVER_API_URL + 'api/sys-coupon-classifies';

    constructor(protected http: HttpClient) {}

    create(sysCouponClassify: ISysCouponClassify): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCouponClassify);
        return this.http
            .post<ISysCouponClassify>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysCouponClassify: ISysCouponClassify): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCouponClassify);
        return this.http
            .put<ISysCouponClassify>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysCouponClassify>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysCouponClassify[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysCouponClassify: ISysCouponClassify): ISysCouponClassify {
        const copy: ISysCouponClassify = Object.assign({}, sysCouponClassify, {
            createTime:
                sysCouponClassify.createTime != null && sysCouponClassify.createTime.isValid()
                    ? sysCouponClassify.createTime.toJSON()
                    : null,
            updateTime:
                sysCouponClassify.updateTime != null && sysCouponClassify.updateTime.isValid()
                    ? sysCouponClassify.updateTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysCouponClassify: ISysCouponClassify) => {
                sysCouponClassify.createTime = sysCouponClassify.createTime != null ? moment(sysCouponClassify.createTime) : null;
                sysCouponClassify.updateTime = sysCouponClassify.updateTime != null ? moment(sysCouponClassify.updateTime) : null;
            });
        }
        return res;
    }
}
