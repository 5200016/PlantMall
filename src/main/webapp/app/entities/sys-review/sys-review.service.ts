import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysReview } from 'app/shared/model/sys-review.model';

type EntityResponseType = HttpResponse<ISysReview>;
type EntityArrayResponseType = HttpResponse<ISysReview[]>;

@Injectable({ providedIn: 'root' })
export class SysReviewService {
    public resourceUrl = SERVER_API_URL + 'api/sys-reviews';

    constructor(protected http: HttpClient) {}

    create(sysReview: ISysReview): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysReview);
        return this.http
            .post<ISysReview>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysReview: ISysReview): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysReview);
        return this.http
            .put<ISysReview>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysReview>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysReview[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysReview: ISysReview): ISysReview {
        const copy: ISysReview = Object.assign({}, sysReview, {
            createTime: sysReview.createTime != null && sysReview.createTime.isValid() ? sysReview.createTime.toJSON() : null,
            updateTime: sysReview.updateTime != null && sysReview.updateTime.isValid() ? sysReview.updateTime.toJSON() : null
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
            res.body.forEach((sysReview: ISysReview) => {
                sysReview.createTime = sysReview.createTime != null ? moment(sysReview.createTime) : null;
                sysReview.updateTime = sysReview.updateTime != null ? moment(sysReview.updateTime) : null;
            });
        }
        return res;
    }
}
