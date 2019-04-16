/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysReviewComponent } from 'app/entities/sys-review/sys-review.component';
import { SysReviewService } from 'app/entities/sys-review/sys-review.service';
import { SysReview } from 'app/shared/model/sys-review.model';

describe('Component Tests', () => {
    describe('SysReview Management Component', () => {
        let comp: SysReviewComponent;
        let fixture: ComponentFixture<SysReviewComponent>;
        let service: SysReviewService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysReviewComponent],
                providers: []
            })
                .overrideTemplate(SysReviewComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysReviewComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysReviewService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysReview(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysReviews[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
