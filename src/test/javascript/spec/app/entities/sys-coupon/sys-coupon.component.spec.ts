/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponComponent } from 'app/entities/sys-coupon/sys-coupon.component';
import { SysCouponService } from 'app/entities/sys-coupon/sys-coupon.service';
import { SysCoupon } from 'app/shared/model/sys-coupon.model';

describe('Component Tests', () => {
    describe('SysCoupon Management Component', () => {
        let comp: SysCouponComponent;
        let fixture: ComponentFixture<SysCouponComponent>;
        let service: SysCouponService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponComponent],
                providers: []
            })
                .overrideTemplate(SysCouponComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysCoupon(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysCoupons[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
