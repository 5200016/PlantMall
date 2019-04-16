import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysReview } from 'app/shared/model/sys-review.model';
import { SysReviewService } from './sys-review.service';
import { SysReviewComponent } from './sys-review.component';
import { SysReviewDetailComponent } from './sys-review-detail.component';
import { SysReviewUpdateComponent } from './sys-review-update.component';
import { SysReviewDeletePopupComponent } from './sys-review-delete-dialog.component';
import { ISysReview } from 'app/shared/model/sys-review.model';

@Injectable({ providedIn: 'root' })
export class SysReviewResolve implements Resolve<ISysReview> {
    constructor(private service: SysReviewService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysReview> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysReview>) => response.ok),
                map((sysReview: HttpResponse<SysReview>) => sysReview.body)
            );
        }
        return of(new SysReview());
    }
}

export const sysReviewRoute: Routes = [
    {
        path: 'sys-review',
        component: SysReviewComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReview.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-review/:id/view',
        component: SysReviewDetailComponent,
        resolve: {
            sysReview: SysReviewResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReview.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-review/new',
        component: SysReviewUpdateComponent,
        resolve: {
            sysReview: SysReviewResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReview.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-review/:id/edit',
        component: SysReviewUpdateComponent,
        resolve: {
            sysReview: SysReviewResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReview.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysReviewPopupRoute: Routes = [
    {
        path: 'sys-review/:id/delete',
        component: SysReviewDeletePopupComponent,
        resolve: {
            sysReview: SysReviewResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysReview.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
