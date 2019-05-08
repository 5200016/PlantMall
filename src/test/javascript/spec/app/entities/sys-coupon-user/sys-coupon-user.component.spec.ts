/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponUserComponent } from 'app/entities/sys-coupon-user/sys-coupon-user.component';
import { SysCouponUserService } from 'app/entities/sys-coupon-user/sys-coupon-user.service';
import { SysCouponUser } from 'app/shared/model/sys-coupon-user.model';

describe('Component Tests', () => {
    describe('SysCouponUser Management Component', () => {
        let comp: SysCouponUserComponent;
        let fixture: ComponentFixture<SysCouponUserComponent>;
        let service: SysCouponUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponUserComponent],
                providers: []
            })
                .overrideTemplate(SysCouponUserComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponUserComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponUserService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysCouponUser(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysCouponUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
